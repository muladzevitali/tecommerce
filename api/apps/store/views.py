from rest_framework import permissions
from rest_framework import viewsets

from .models import Category, Product
from .pagination import CategoryListPagination
from .serializers import CategorySerializer, ProductSerializer


class CategoryVS(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticated]
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    pagination_class = CategoryListPagination


class ProductVS(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticated]
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
