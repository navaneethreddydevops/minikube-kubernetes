pipeline {
  agent any
  options {
    parallelsAlwaysFailFast()
  }
  stages {
    stage('Build') {
      steps {
          sh '''
          /usr/local/bin/eksctl --version
          '''
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
