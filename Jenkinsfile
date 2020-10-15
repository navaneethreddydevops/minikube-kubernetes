import hudson.util.RemotingDiagnostics
import jenkins.model.Jenkins
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL

pipeline {
  agent {
  label 'docker'
  }
  options {
    timestamps()
  }
  stages {
    stage('Build') {
      agent {
          any
      }
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
