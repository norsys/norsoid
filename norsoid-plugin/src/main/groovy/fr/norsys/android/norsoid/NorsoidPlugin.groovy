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
        project.pluginManager.apply('com.neenbedankt.android-apt')
        project.pluginManager.apply('realm-android')

        project.android.lintOptions {
            disable 'InvalidPackage'
        }

        project.dependencies {
            compile "fr.norsys.android.norsoid:norsoid-library:$project.NORSOID_VERSION"
            apt "fr.norsys.android.norsoid:norsoid-library:$project.NORSOID_VERSION"
            apt "com.google.dagger:dagger-compiler:$project.DAGGER_VERSION"
            apt "com.jakewharton:butterknife-compiler:$project.BUTTERKNIFE_VERSION"
            provided "org.projectlombok:lombok:$project.LOMBOK_VERSION"
            apt "org.projectlombok:lombok:$project.LOMBOK_VERSION"
        }
    }
}