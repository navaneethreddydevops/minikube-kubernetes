pipeline {
  agent any
  options {
    parallelsAlwaysFailFast()
  }
  environment {
        AWS_ACCESS_KEY=''
        AWS_SECRET_KEY=''
        AWS_SESSION_TOKEN=''
    }
  parameters{
      string(name: 'AWS_ACCESS_KEY', defaultValue: '', description: 'ACCESS_KEY')
      string(name: 'AWS_SECRET_KEY', defaultValue: '', description: 'SECRET_KEY')
      string(name: 'AWS_SESSION_TOKEN', defaultValue: '', description: 'AWS_SESSION_TOKEN')
  }
  stages {
    stage('Environmnet') {
      steps {
        export AWS_ACCESS_KEY=${params.AWS_ACCESS_KEY}
        export AWS_ACCESS_KEY=${params.AWS_SECRET_KEY}
        export AWS_ACCESS_KEY=${arams.AWS_SESSION_TOKEN}
          }
        }
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
