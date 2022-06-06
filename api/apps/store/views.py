from rest_framework import viewsets, permissions
from apps.user.permissions import OwnUpdatePermission, AnyCanCreatePermission

from .models import Category, Product
from .serializers import CategorySerializer, ProductSerializer
from .pagination import CategoryListPagination


class CategoryVS(viewsets.ModelViewSet):
    # permission_classes = [permissions.IsAuthenticated | OwnUpdatePermission | AnyCanCreatePermission]
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    pagination_class = CategoryListPagination


class ProductVS(viewsets.ModelViewSet):
    # permission_classes = [permissions.IsAuthenticated | OwnUpdatePermission | AnyCanCreatePermission]
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
