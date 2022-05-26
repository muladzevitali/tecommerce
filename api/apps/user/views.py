from rest_framework import permissions
from rest_framework import viewsets

from .models import User
from .permissions import (OwnUpdatePermission, AnyCanCreatePermission)
from .serializers import UserSerializer


class UserViewSet(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticated | OwnUpdatePermission | AnyCanCreatePermission]
    queryset = User.objects.all().order_by('-created_at')
    serializer_class = UserSerializer
