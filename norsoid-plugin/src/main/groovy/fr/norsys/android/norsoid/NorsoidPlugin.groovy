package fr.norsys.android.norsoid

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion

class NorsoidPlugin implements Plugin<Project> {
    @Override void apply(Project project) {
        def hasApp = project.plugins.withType(AppPlugin)
        def hasLib = project.plugins.withType(LibraryPlugin)

        if (!hasApp && !hasLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }


        project.plugins.apply('realm-android')
        project.plugins.apply('me.tatarka.retrolambda')
        project.plugins.apply('io.fabric')

        project.android {

            defaultConfig {
                multiDexEnabled true
            }

            compileOptions {
                sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
            }

            dexOptions {
                javaMaxHeapSize "4g" //specify the heap size for the dex process
            }

            lintOptions {
                disable 'InvalidPackage'
            }

        }

        project.repositories {
            maven { url 'https://maven.fabric.io/public' }
        }

        project.dependencies {
            compile "fr.norsys.android.norsoid:norsoid-library:${NorsoidConstants.NORSOID_VERSION}"
            annotationProcessor "fr.norsys.android.norsoid:norsoid-library:${NorsoidConstants.NORSOID_VERSION}"
            annotationProcessor "com.google.dagger:dagger-compiler:${NorsoidConstants.DAGGER_VERSION}"
            annotationProcessor "com.jakewharton:butterknife-compiler:${NorsoidConstants.BUTTERKNIFE_VERSION}"
            provided "org.projectlombok:lombok:${NorsoidConstants.LOMBOK_VERSION}"

            compile("com.crashlytics.sdk.android:crashlytics:${NorsoidConstants.CRASHLYTICS_VERSION}@aar") {
                transitive = true;
            }
        }

        generateLombokConfig(project)
    }

    public void generateLombokConfig(Project project) {
        def file = new File("${project.projectDir}/lombok.config")
        if(!file.exists()) {
            file.write("lombok.addGeneratedAnnotation = false")
        }
    }
}