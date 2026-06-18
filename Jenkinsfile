pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/tMFZ223/saucedemoUiTestsProject.git'
            }
        }

        stage('Clean Project') {
            steps {
                bat "mvn clean"
            }
        

        stage('Parallel tests') {
            parallel {

                stage('Chrome') {
                    steps {
                        bat 'mvn test -P chrome'
                    }
                }

                stage('Firefox') {
                    steps {
                        bat 'mvn test -P firefox'
                    }
                }

            }
        }
    }

    post {

        always {

            junit(
                testResults: 'target/surefire-reports/**/*.xml',
                allowEmptyResults: true
            )

            allure(
                includeProperties: false,
                results: [[path: 'target/allure-results']]
            )

            publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'TestNG Report'
            ])
        }
    }
}