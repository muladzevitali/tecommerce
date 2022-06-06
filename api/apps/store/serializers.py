from rest_framework import serializers
from .models import Category, Product, ProductGallery


class CategorySerializer(serializers.ModelSerializer):

    class Meta:
        model = Category
        fields = '__all__'


class ProductGallerySerializer(serializers.ModelSerializer):

    class Meta:
        model = ProductGallery
        fields = '__all__'


class ProductSerializer(serializers.ModelSerializer):

    product_gallery = ProductGallerySerializer(many=True, read_only=True)

    class Meta:
        model = Product
        fields = '__all__'


