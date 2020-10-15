pipeline {
  agent any
  options {
    parallelsAlwaysFailFast()
  }
  stages {
    stage('Build') {
      steps {
        dir("${env.WORKSPACE}/aws-eks-cluster"){
          sh '''
          /usr/local/bin/eksctl create cluster -f cluster.yaml
          '''
          }
        }
      }
    }
  }
