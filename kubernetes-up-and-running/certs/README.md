Creating Secret In Kubernetes
```
kubectl create secret generic kuard-tls --from-file=kuard.crt --from-file=kuard.key
```