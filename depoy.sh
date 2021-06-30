#!/usr/bin/env bash

shell ('''
      echo """Installing the eksctl"""
      curl --silent --location "https://github.com/eksctl_$(uname -s)_amd64.tar.gz" | tar -xvzf -C /tmp
      mv /tmp/eksctl/ /usr/local/bin

      instance_id =$(curl http://169.254.169.254/latest/meta-data/instance-id)


      aws ec2 modify-instance-metadata-options  \
      --instance-id=$instance_id \
      --http-put-response-hop-limit 2 \
      --http-endpoint enabled \
      --region us-east-1

      python3 ./deploy.py && \
      namespace=$(kubectl get ns | grep -i navaneeth \
                  | awk '{print $1}') && \

      loadbalancerURL=$$(kubectl get svc --namespace=$namespace \
                  grep -i LoadBalancer | awk '{print $4}') && \

      aws route53 change-resource-record-sets \
      --hosted-zone-id XHSGHDKFJFJ \
      --change-batch file://CNAME.json

        ''')
