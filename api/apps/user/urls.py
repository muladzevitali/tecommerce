from django.urls import (path, include)
from rest_framework import routers
from rest_framework.authtoken.views import obtain_auth_token

from .views import UserViewSet

app_name = 'user'

router = routers.DefaultRouter()
router.register(r'users', UserViewSet, basename='User')

urlpatterns = (path('', include(router.urls)),
               path('token', obtain_auth_token, name='token_auth'))
