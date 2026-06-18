pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    parameters {
        choice(
                name: 'BROWSER',
                choices: ['chrome', 'firefox'],
                description: 'Select browser')
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                        url: 'https://github.com/tMFZ223/saucedemoUiTestsProject.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn clean test -P %BROWSER%"
            }
        }
    }

    post {

        always {

            junit '**/surefire-reports/*.xml'

            allure(
                    results: [[path: 'target/allure-results']]
            )

            archiveArtifacts(
                    artifacts: 'target/screenshots/**/*.png',
                    fingerprint: true,
                    allowEmptyArchive: true
            )
        }
    }
}