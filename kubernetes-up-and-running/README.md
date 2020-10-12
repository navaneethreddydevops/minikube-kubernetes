kubectl get componentstatuses

It should return the below output
NAME                 STATUS    MESSAGE             ERROR
controller-manager   Healthy   ok                  
scheduler            Healthy   ok                  
etcd-0               Healthy   {"health":"true"}  
# Describe node details:
kubectl describe nodes ip-192-168-30-192.us-west-2.compute.internal

# Describing the Kube-proxy and it will be a deamonset running on all nodes of cluster

kubectl get daemonSets --namespace=kube-system kube-proxy

# Core DNS also will be a Deployment running on all the nodes
kubectl get deployments --namespace=kube-system coredns
kubectl get services --namespace=kube-system kube-dns

# Deploying the Kube-proxy on EKS Cluster
kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/download/v0.3.6/components.yaml
kubectl get deployment metrics-server -n kube-system
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta8/aio/deploy/recommended.yaml
vi eks-admin-service-account.yaml
kubectl apply -f eks-admin-service-account.yaml
kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep eks-admin | awk '{print $1}')