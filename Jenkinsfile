#!/usr/bin/groovy
import hudson.model.*
import hudson.EnvVars
import groovy.json.JsonSlurperClassic
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import java.net.URL
pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                echo "Building Stage"
            }
        }
        stage('Test') { 
            steps {
                echo "Building Test Stage"
            }
        }
        stage('Deploy') { 
            steps {
                echo "Building Deploy Stage"
            }
        }
    }
}