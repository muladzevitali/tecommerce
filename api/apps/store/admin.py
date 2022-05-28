import admin_thumbnails
from django.contrib import admin

from apps.store.models import (Category, Product, ProductGallery)


@admin.register(Category)
class CategoryAdmin(admin.ModelAdmin):
    list_display = ('name', 'parent')


@admin_thumbnails.thumbnail('image')
class ProductGalleryInline(admin.TabularInline):
    model = ProductGallery
    extra = 1


@admin.register(Product)
class ProductAdmin(admin.ModelAdmin):
    list_display = ['name', 'price', 'stock', 'category', 'modified', 'is_available']
    list_editable = ('is_available',)
    inlines = (ProductGalleryInline,)
    search_fields = ('name',)

    def get_views(self, obj):
        return obj.views.count

    get_views.short_description = 'Product views'
