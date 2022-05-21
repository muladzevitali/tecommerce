from .env import env


REST_FRAMEWORK = {
    # Use Django's standard `django.contrib.auth` permissions,
    # or allow read-only access for unauthenticated users.
    'DEFAULT_PERMISSION_CLASSES': [
        'rest_framework.permissions.IsAuthenticated',
    ],
    'DEFAULT_AUTHENTICATION_CLASSES': (
        'app_auth.authentication.KongAuthentication',
    ),
    'DEFAULT_PARSER_CLASSES': (
        'djangorestframework_camel_case.parser.CamelCaseJSONParser',
    ),
    'DEFAULT_RENDERER_CLASSES': (
        'djangorestframework_camel_case.render.CamelCaseJSONRenderer',
        'djangorestframework_camel_case.render.CamelCaseBrowsableAPIRenderer',
    ),
    'JSON_UNDERSCOREIZE': {
        'no_underscore_before_number': True,
    },
    'DEFAULT_SCHEMA_CLASS': 'drf_spectacular.openapi.AutoSchema',
}

EXTRA_SERVERS_FOR_LOCAL_ENVIRONMENT = [
    {
        'url': 'https://services-staging.stacktome.com',
        'description': 'STAGING'
    },
    {
        'url': 'https://services.stacktome.com',
        'description': 'PRODUCTION'
    },
] if env('APP_ENVIRONMENT') == 'local' else []

# OpenApi schema generation
SPECTACULAR_SETTINGS = {
    'TITLE': 'Stacktome Services API',
    'DESCRIPTION': 'OpenApiSchema 3 for Stacktome services.',
    'VERSION': '1.0.0',
    'SERVERS': [
        {
          'url': env('DJANGO_SITE_URL'),
          'description': env('APP_ENVIRONMENT').upper()
        },
    ] + EXTRA_SERVERS_FOR_LOCAL_ENVIRONMENT,
    'TAGS': [
        {'name': 'auth', 'description': 'Authentication and authorization endpoints'},
        {'name': 'connections', 'description': 'Connections management'},
        {'name': 'imports', 'description': 'Customer data imports'},
        {'name': 'jobs', 'description': 'Statuses of jobs processing'},
        {'name': 'payments', 'description': 'Management of customer payment operations'},
        {'name': 'publish', 'description': 'Configurations for publishing'},
        {'name': 'segments', 'description': 'Segments management'},
        {'name': 'users', 'description': 'Users data management'},
    ],

    # OTHER SETTINGS
    'COMPONENT_SPLIT_REQUEST': True,
    'COMPONENT_SPLIT_PATCH': False,
    'CAMELIZE_NAMES': True,
    'SERVE_AUTHENTICATION': [
        'app_auth.authentication.AccessTokenScheme',
    ],
    'PREPROCESSING_HOOKS': [
        'mysite.utils.exclude_private_endpoints_hook'
    ],
    'POSTPROCESSING_HOOKS': [
        'drf_spectacular.contrib.djangorestframework_camel_case.camelize_serializer_fields'
    ],
}
