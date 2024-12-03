pipeline {
    agent any

    tools {
        jdk 'Java 21'
        maven 'M3'
    }

    parameters {
        choice(name: 'ENV', choices: ['qa', 'dev', 'sandbox', 'uat', 'stage', 'prod'], description: 'The environment to run tests against')
        choice(name: 'SUITE', choices: ['testng', 'smoke', 'regression', 'cross-browser'], description: 'The test suite to run')
    }

    stages {
        stage('Check Dependencies') {
            steps {
                sh "java -version"
                sh "mvn -v"
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    sh "mvn clean test -Denv=${params.ENV} -Dsuite=${params.SUITE}"
                }
            }
        }
    }

    post {
        always {
            script {
                allure([
                    jdk: 'Java 21',
                    includeProperties: false,
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
