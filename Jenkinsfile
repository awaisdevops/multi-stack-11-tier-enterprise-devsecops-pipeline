pipeline {
    agent any

    stages {
        stage('Build & Tag Docker Image') {
            steps {
                script {
                    dir('src') {

                    withDockerRegistry(credentialsId: 'docker-hub-repo', toolName: 'docker') {
                        sh "docker build -t awaisakram11199/devopsimages:cartservice01 ."
                    }
                        }
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker-hub-repo', toolName: 'docker') {
                        sh "docker pushawaisakram11199/devopsimages:cartservice01 "
                    }
                }
            }
        }
    }
}
