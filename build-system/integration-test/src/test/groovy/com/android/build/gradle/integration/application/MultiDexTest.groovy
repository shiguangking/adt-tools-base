/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.build.gradle.integration.application

import com.android.build.gradle.integration.common.category.DeviceTests
import com.android.build.gradle.integration.common.fixture.GradleTestProject
import com.google.common.io.Files
import groovy.transform.CompileStatic
import org.gradle.api.JavaVersion
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category

import static com.android.build.gradle.integration.common.truth.TruthHelper.assertThat
import static com.android.build.gradle.integration.common.truth.TruthHelper.assertThatApk
import static com.android.build.gradle.integration.common.truth.TruthHelper.assertThatZip
import static com.android.builder.model.AndroidProject.FD_INTERMEDIATES
/**
 * Assemble tests for multiDex.
 */
@CompileStatic
class MultiDexTest {
    @Rule
    public GradleTestProject project = GradleTestProject.builder()
            .fromTestProject("multiDex")
            .withHeap("2048M")
            .create()

    @Test
    void "check normal build"() {
        project.execute("assembleDebug", "assembleAndroidTest")

        def expected = [
                "com/android/tests/basic/Used",
                "com/android/tests/basic/DeadCode",
                "com/android/tests/basic/Main"
        ]

        if (JavaVersion.current().isJava8Compatible()) {
            // javac 1.8 puts the InnerClasses attribute from R to R$id inside classes that use
            // R$id, like Main. The main dex list builder picks it up from the constant pool.
            expected += [
                    'com/android/tests/basic/R',
                    'com/android/tests/basic/R$id',
                    'com/android/tests/basic/R$layout',
            ]
        }

        assertMainDexListContains("debug", expected)

        // manually inspect the apk to ensure that the classes.dex that was created is the same
        // one in the apk. This tests that the packaging didn't rename the multiple dex files
        // around when we packaged them.
        File classesDex = GradleTestProject.USE_JACK ?
                project.file("build/" + FD_INTERMEDIATES + "/dex/ics/debug/classes.dex") :
                project.file("build/" + FD_INTERMEDIATES + "/transforms/dex/ics/debug/" +
                        "folders/1000/1f/main/classes.dex")

        assertThatZip(project.getApk("ics", "debug")).containsFileWithContent(
                "classes.dex",
                Files.toByteArray(classesDex))

        File classes2Dex = GradleTestProject.USE_JACK ?
                project.file("build/" + FD_INTERMEDIATES + "/dex/ics/debug/classes2.dex") :
                project.file("build/" + FD_INTERMEDIATES + "/transforms/dex/ics/debug/" +
                        "folders/1000/1f/main/classes2.dex")

        assertThatZip(project.getApk("ics", "debug")).containsFileWithContent(
                "classes2.dex",
                Files.toByteArray(classes2Dex))

        commonApkChecks("debug")

        assertThatApk(project.getTestApk("ics", "debug")).
                doesNotContainClass("Landroid/support/multidex/MultiDexApplication;")
        assertThatApk(project.getTestApk("lollipop", "debug")).
                doesNotContainClass("Landroid/support/multidex/MultiDexApplication;")

        // Both test APKs should contain a class from Junit.
        assertThatApk(project.getTestApk("ics", "debug")).containsClass("Lorg/junit/Assert;")
        assertThatApk(project.getTestApk("lollipop", "debug")).containsClass("Lorg/junit/Assert;")

        assertThatApk(project.getApk("ics", "debug"))
                .containsClass("Lcom/android/tests/basic/NotUsed;")
        assertThatApk(project.getApk("ics", "debug"))
                .containsClass("Lcom/android/tests/basic/DeadCode;")
    }

    @Test
    void "check minified build"() {
        project.execute("assembleMinified")

        assertMainDexListContains(
                "minified",
                [ "com/android/tests/basic/Used", "com/android/tests/basic/Main"])

        commonApkChecks("minified")

        assertThatApk(project.getApk("ics", "minified"))
                .doesNotContainClass("Lcom/android/tests/basic/NotUsed;")
        assertThatApk(project.getApk("ics", "minified"))
                .doesNotContainClass("Lcom/android/tests/basic/DeadCode;")
    }

    private void commonApkChecks(String buildType) {
        assertThatApk(project.getApk("ics", buildType)).
                containsClass("Landroid/support/multidex/MultiDexApplication;")
        assertThatApk(project.getApk("lollipop", buildType)).
                doesNotContainClass("Landroid/support/multidex/MultiDexApplication;")

        assertThatApk(project.getApk("ics", buildType))
                .containsClass("Lcom/android/tests/basic/Main;")
        assertThatApk(project.getApk("ics", buildType))
                .containsClass("Lcom/android/tests/basic/Used;")
        assertThatApk(project.getApk("ics", buildType))
                .containsClass("Lcom/android/tests/basic/Kept;")
    }

    private assertMainDexListContains(String buildType, List<String> expected) {
        // Jack do not produce maindexlist.txt
        if (GradleTestProject.USE_JACK) {
            return
        }
        File listFile = project.file("build/intermediates/multi-dex/ics/${buildType}/maindexlist.txt")
        Iterable<String> actual = listFile
                .readLines()
                .findAll{!it.isEmpty() && !it.startsWith("android/support/multidex")}
                .collect{it - ~/.class$/}

        assertThat(actual).containsExactlyElementsIn(expected.toList())
    }

    @Test
    @Category(DeviceTests.class)
    void connectedCheck() {
        project.executeConnectedCheck()
    }
}
