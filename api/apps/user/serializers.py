from rest_framework import serializers

from .models import User


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ("email", "first_name", "last_name", "city", "address", "zip_code", "password")
        update_readonly_fields = ('email', 'id', 'created_at', 'password', 'is_superuser', 'is_staff')
        create_readonly_fields = ('is_superuser', 'is_staff')
        extra_kwargs = {'password': {'write_only': True}}

    def __init__(self, *args, **kwargs):
        super(UserSerializer, self).__init__(*args, **kwargs)
        if self.context['request'].method == "PUT":
            self.fields.pop('password', None)

    def create(self, validated_data):
        for field_name in self.Meta.create_readonly_fields:
            validated_data.pop(field_name, None)
        user = super().create(validated_data)
        user.set_password(validated_data['password'])
        user.save()

        return user

    def update(self, instance, validated_data):
        for field_name in self.Meta.update_readonly_fields:
            validated_data.pop(field_name, None)

        return super().update(instance, validated_data)
