from django.urls import (path, include)
from rest_framework.routers import DefaultRouter

from .views import CategoryVS, ProductVS


router = DefaultRouter()
router.register('category', CategoryVS, basename='category')
router.register('product', ProductVS, basename='product')


urlpatterns = [
    path('', include(router.urls))
]
