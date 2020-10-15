pipeline {
  agent any
  options {
    parallelsAlwaysFailFast()
  }
  stages {
    stage('Build') {
      steps {
        dir("${env.WORKSPACE}/"){
          sh '''
          /usr/local/bin/eksctl
          '''
          }
        }
      }
    }
  }
  post {
    failure {
      // notify users when the Pipeline fails
      mail to: 'navaneethreddydevops@gmail.com',
          subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
          body: "Something is wrong with ${env.BUILD_URL}"
    }
  }
