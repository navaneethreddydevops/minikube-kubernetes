import hudson.util.RemotingDiagnostics
import jenkins.model.Jenkins
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL

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
    stage('Checkout') {
      steps {
        def env = System.getenv();
        System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', env['JENKINS_TIMEZONE']);
       }
    }
  }
}