pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {

        stage('Parallel Tests') {

            parallel {

stage('Chrome') {

    steps {

        dir('chromeWorkspace') {

            git branch: 'master',
                url: 'https://github.com/tMFZ223/SaucedemoUiTestsProject.git'

            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                bat 'mvn clean test -P chrome -Dmaven.repo.local=.m2'

                stash(
                    name: 'chrome-allure',
                    includes: 'target/allure-results/**',
                    allowEmpty: true
                )

                stash(
                    name: 'chrome-surefire',
                    includes: 'target/surefire-reports/**',
                    allowEmpty: true
                )
            }
        }
    }
}

stage('Firefox') {

    steps {

        dir('firefoxWorkspace') {

            git branch: 'master',
                url: 'https://github.com/tMFZ223/SaucedemoUiTestsProject.git'

            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                bat 'mvn clean test -P firefox -Dmaven.repo.local=.m2'

                stash(
                    name: 'firefox-allure',
                    includes: 'target/allure-results/**',
                    allowEmpty: true
                )

                stash(
                    name: 'firefox-surefire',
                    includes: 'target/surefire-reports/**',
                    allowEmpty: true
                )
            }
        }
    }
}

stage('Collect Reports') {

    steps {

        script {

            dir('mergedReports/chrome') {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    unstash 'chrome-allure'
                    unstash 'chrome-surefire'
                }
            }

            dir('mergedReports/firefox') {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    unstash 'firefox-allure'
                    unstash 'firefox-surefire'
                }
            }
        }
    }
}

post {

    always {

        junit(
            testResults: '''
                mergedReports/chrome/target/surefire-reports/**/*.xml,
                mergedReports/firefox/target/surefire-reports/**/*.xml
            ''',
            allowEmptyResults: true
        )

        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

            allure(
                includeProperties: false,
                results: [
                    [path: 'mergedReports/chrome/target/allure-results'],
                    [path: 'mergedReports/firefox/target/allure-results']
                ]
            )

        }

        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'mergedReports/chrome/target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'Chrome TestNG Report'
            ])

        }

        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'mergedReports/firefox/target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'Firefox TestNG Report'
            ])

        }
    }
}
}