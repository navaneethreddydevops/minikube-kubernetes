Creating Secret In Kubernetes
```
kubectl create secret generic kuard-tls --from-file=kuard.crt --from-file=kuard.key

kubectl apply -f pod.yaml

kubectl port-forward kuard-tls 8443:8443
```


```
kubectl create secret docker-registry my-image-pull-secret --docker-username=XXXXXXXX --docker-password=XXXXXXX --docker-email=navaneethreddydevops@gmail.com

```