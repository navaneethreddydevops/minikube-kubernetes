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

  environment {
    IMAGE = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
  }

  stages {
    stage('Build') {
      agent {
        docker {
          reuseNode true
          image 'maven:3.5.0-jdk-8'
          volume '/var/run/docker.sock:/var/run/docker.sock'
        }
      }
      steps {
        withMaven(options: [findbugsPublisher(), junitPublisher(ignoreAttachments: false)]) {
          sh 'mvn clean findbugs:findbugs package'
        }
      }
      post {
        success {
          archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
        }
      }
    }

    stage('Quality Analysis') {
      parallel {
        // run Sonar Scan and Integration tests in parallel. This syntax requires Declarative Pipeline 1.2 or higher
        stage ('Integration Test') {
          agent any  //run this stage on any available agent
          steps {
            echo 'Run integration tests here...'
          }
        }
        stage('Sonar Scan') {
          agent {
            docker {
              // we can use the same image and workspace as we did previously
              reuseNode true
              image 'maven:3.5.0-jdk-8'
              volume '/var/run/docker.sock:/var/run/docker.sock'
            }
          }
          environment {
            //use 'sonar' credentials scoped only to this stage
            SONAR = credentials('sonar')
          }
          steps {
            sh 'mvn sonar:sonar -Dsonar.login=$SONAR_PSW'
          }
        }
      }
    }

    stage('Build and Publish Image') {
      when {
        branch 'master'  //only run these steps on the master branch
      }
      steps {
        /*
         * Multiline strings can be used for larger scripts. It is also possible to put scripts in your shared library
         * and load them with 'libaryResource'
         */
        sh """
          docker build -t ${IMAGE} .
          docker tag ${IMAGE} ${IMAGE}:${VERSION}
          docker push ${IMAGE}:${VERSION}
        """
      }
    }
  }

  post {
    failure {
      // notify users when the Pipeline fails
      mail to: 'team@example.com',
          subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
          body: "Something is wrong with ${env.BUILD_URL}"
    }
  }
}
