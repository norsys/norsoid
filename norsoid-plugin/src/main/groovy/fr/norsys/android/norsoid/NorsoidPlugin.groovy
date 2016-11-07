package fr.norsys.android.norsoid

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class NorsoidPlugin implements Plugin<Project> {
    @Override void apply(Project project) {
        def hasApp = project.plugins.withType(AppPlugin)
        def hasLib = project.plugins.withType(LibraryPlugin)

        if (!hasApp && !hasLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }

        project.plugins.apply('com.neenbedankt.android-apt')
        project.plugins.apply('realm-android')

        project.android.lintOptions {
            disable 'InvalidPackage'
        }
        project.dependencies {
            compile "fr.norsys.android.norsoid:norsoid-library:${NorsoidConstants.NORSOID_VERSION}"
            apt "fr.norsys.android.norsoid:norsoid-library:${NorsoidConstants.NORSOID_VERSION}"
            apt "com.google.dagger:dagger-compiler:${NorsoidConstants.DAGGER_VERSION}"
            apt "com.jakewharton:butterknife-compiler:${NorsoidConstants.BUTTERKNIFE_VERSION}"
            provided "org.projectlombok:lombok:${NorsoidConstants.LOMBOK_VERSION}"
            apt "org.projectlombok:lombok:${NorsoidConstants.LOMBOK_VERSION}"
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