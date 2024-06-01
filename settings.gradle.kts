pluginManagement {
    repositories {

        mavenCentral()

        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }

    }

}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://s01.oss.sonatype.org/content/groups/public") }
    }
}

rootProject.name = "WiniyNews"
include(":app")
