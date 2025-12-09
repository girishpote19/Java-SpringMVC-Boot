pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    environment {
        // Sonar token stored as "Secret Text" in Jenkins Credentials
        SONAR_AUTH = credentials('sonar-token')

        // Jenkins → Manage Jenkins → Configure System → SonarQube Servers
        // Name used here must match that config
        SONAR_SERVER = 'Sonar'
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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('Sonar') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=books-crud \
                        -Dsonar.host.url=$SONAR_HOST_URL \
                        -Dsonar.login=${SONAR_AUTH}
                    """
                }
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
            script {
                cleanWs()
            }
        }
    }
}
