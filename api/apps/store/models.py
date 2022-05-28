from django.db import models
from model_utils.models import TimeStampedModel


class Category(TimeStampedModel):
    name = models.CharField(max_length=100)
    description = models.CharField(max_length=255, blank=True, null=True)
    parent = models.ForeignKey('Category', on_delete=models.CASCADE, related_name='subcategories', blank=True,
                               null=True)

    class Meta:
        verbose_name = 'category'
        verbose_name_plural = 'categories'

    def __str__(self):
        return self.name


class Product(TimeStampedModel):
    name = models.CharField(max_length=200)
    description = models.TextField(max_length=500, blank=True)
    image = models.ImageField(upload_to='photos/products', max_length=255)
    price = models.DecimalField(verbose_name='price', max_digits=8, decimal_places=2)
    stock = models.IntegerField()
    is_available = models.BooleanField(default=True)
    category = models.ForeignKey('Category', on_delete=models.DO_NOTHING, null=True)

    def __str__(self):
        return self.name


class ProductGallery(models.Model):
    product = models.ForeignKey('Product', on_delete=models.CASCADE, related_name='images')
    image = models.ImageField(upload_to='photos/products', max_length=255)

    class Meta:
        verbose_name = 'Product gallery'
        verbose_name_plural = 'Product gallery'

    def __str__(self):
        return f'{self.product.name}'
