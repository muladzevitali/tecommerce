from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from apps.user import models


@admin.register(models.City)
class CityAdmin(admin.ModelAdmin):
    list_display = ("pk", "name")


@admin.register(models.User)
class UserAdmin(UserAdmin):
    ordering = ('created_at',)
    list_display = ('email', 'first_name', 'last_name', 'is_superuser', 'is_staff', 'is_active')
    fieldsets = (
        (None, {'fields': ('email', 'password')}),
        ('Personal info', {'fields': ('first_name', 'last_name')}),
        ('Permissions', {'fields': ('is_active', 'is_staff', 'is_superuser',
                                    'groups', 'user_permissions')}),

    )

    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('email', 'password', 'password2')}
         ),
    )
