pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/girishpote19/Java-SpringMVC-Boot.git', branch: 'BooksCrudOperations'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    }

    post {
        success {
            emailext(
                to: "potegirish6@gmail.com",
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build succeeded!\nDetails: ${env.BUILD_URL}"
            )
        }

        failure {
            emailext(
                to: "potegirish6@gmail.com",
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Build failed!\nDetails: ${env.BUILD_URL}"
            )
        }

        cleanup {
            cleanWs()
        }
    }
}