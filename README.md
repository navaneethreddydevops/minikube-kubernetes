kubernetes
```
Kubernetes components
* EKS Control Plane
* Worker Nodes and Node Groups
* Fargate Profiles
* VPC
```
```
eksctl create cluster --name kubernetes-cluster \
                      --region=us-east-1 \
                      --zones=us-east-1a,us-east-1b,us-east-1c
                      --without-nodegroup
```

```
eksctl get clusters
```

aws eks update-kubeconfig --region us-west-2 --name eks-cluster-fargate
