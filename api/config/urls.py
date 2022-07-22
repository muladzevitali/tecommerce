from django.contrib import admin
from django.urls import (path, include, )
from django.conf.urls.static import static
from django.conf import settings
from drf_yasg import openapi
from drf_yasg.views import get_schema_view
from rest_framework import permissions

openapi_info = openapi.Info(
    title="Tecommerce",
    default_version='v1',
    description="Endpoints list")

schema_view = get_schema_view(openapi_info, public=True, permission_classes=[permissions.AllowAny], )

urlpatterns = [
    path('admin/', admin.site.urls),
    path('rest/auth/', include('apps.user.urls')),
    path('rest/swagger/', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    path('rest/store/', include('apps.store.urls'))
]
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

