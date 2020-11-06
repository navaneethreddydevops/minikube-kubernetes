# EKS
```
kubectl apply -k "https://github.com/aws/eks-charts/stable/appmesh-controller/crds?ref=master"
helm repo add eks https://aws.github.io/eks-charts
kubectl create namespace appmesh-system
```
# AppMesh serviceAccount
```
eksctl create iamserviceaccount \
--namespace appmesh-system \
--name appmesh-controller \
--attach-policy-arn arn:aws:iam::aws:policy/AWSCloudMapFullAccess,arn:aws:iam::aws:policy/AWSAppMeshFullAccess,arn:aws:iam::aws:policy/AWSXRayDaemonWriteAccess \
--cluster eks-cluster \
--region us-west-2 \
--approve
```
# Helm Appmesh Installation

```
helm upgrade --install appmesh-controller eks/appmesh-controller \
--namespace appmesh-system \
--set region=us-west-2 \
--set serviceAccount.create=false \
--set serviceAccount.name=appmesh-controller \
--set log.level=debug
```

```
kubectl get pods -n appmesh-system
kubectl describe pod appmesh-controller-774579f796-ng8k5 -n appmesh-system
```

```
kubectl label namespace development mesh=development-mesh
kubectl label namespace development "appmesh.k8s.aws/sidecarInjectorWebhook"=enabled
```