# Prometheus Setup
kubectl get --raw /metrics
kubectl create namespace prometheus
helm install prometheus stable/prometheus --namespace prometheus --set alertmanager.persistentVolume.storageClass="gp2",server.persistentVolume.storageClass="gp2"
kubectl get pods -n prometheus
kubectl --namespace=prometheus port-forward deploy/prometheus-server 9090


# Grafana Installation

helm repo add bitnami https://charts.bitnami.com/bitnami
helm install my-release bitnami/grafana
kubectl port-forward svc/my-release-grafana 8080:3000 &
Username: admin
echo "Password: $(kubectl get secret my-release-grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 --decode)"