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
    always {
        emailext(
            to: "potegirish6@gmail.com",
            subject: "Build Status: ${currentBuild.currentResult}",
            body: "Check details: ${env.BUILD_URL}",
            mimeType: 'text/plain',
            replyTo: '',
            attachLog: false,
            from: "potegirish6@gmail.com",
            smtpHost: "smtp.gmail.com",
            useTLS: true
        )
    }
}

}
