```
eksctl utils associate-iam-oidc-provider --cluster eks-cluster --region us-west-2
```

```
eksctl create iamserviceaccount \
--name resource-api-iam-service-account \
--namespace development \
--cluster eks-cluster \
--attach-policy-arn arn:aws:iam::035612810169:policy/RESOURCE-API-MICROSERVICE --region us-west-2 --approve
```