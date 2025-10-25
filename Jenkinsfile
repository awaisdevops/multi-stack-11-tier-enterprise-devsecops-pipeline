stage('Docker: Build Image') {              
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t awaisakram11199/devopsimages:cartservice01 ."
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh "docker push awaisakram11199/devopsimages:cartservice01"
                    }
                }
            }
        } 
