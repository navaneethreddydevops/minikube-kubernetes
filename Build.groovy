import hudson.util.RemotingDiagnostics
import jenkins.model.Jenkins
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL

def env = System.getenv();

pipeline {
   agent any
   stages {
    stage('Checkout') {
      steps {
        script {
           sh "ls -lart ./*" 
           sh "git branch -a"
          }
       }
    }
  }
}