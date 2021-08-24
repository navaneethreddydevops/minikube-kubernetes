#!/usr/bin/env bash
cd aws-eks-cluster/
eksctl create cluster -f cluster.yaml
