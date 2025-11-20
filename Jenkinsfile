pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/girishpote19/Java-SpringMVC-Boot.git', branch: 'First-RestAPI'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'

            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
		
            }
        }

        stage('Done') {
            steps {
                echo "🎉 Build completed successfully!"
            }
        }
    }
}
