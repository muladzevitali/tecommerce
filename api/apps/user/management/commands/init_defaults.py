from django.core.management.base import BaseCommand
from django.db.models import ObjectDoesNotExist
from django.core.management import call_command
from apps.user.models import User, UserManager


class Command(BaseCommand):
    """Command for initializing data"""
    help = 'Initialize default data'

    def handle(self, *args, **options):
        self._create_admin_if_not_exist()
        call_command('loaddata', 'media/initial/cities.json')
        call_command('loaddata', 'media/initial/categories.json')
        call_command('loaddata', 'media/initial/products.json')
        call_command('loaddata', 'media/initial/products_gallery.json')

    def _create_admin_if_not_exist(self):
        admin_email = 'admin@example.com'
        admin_password = "admin1234"

        try:
            admin = User.objects.get(email=admin_email)

            self.stdout.write(self.style.SUCCESS(f'Administrator {admin} already exists'))
        except ObjectDoesNotExist:
            user_manager = UserManager()
            user_manager.model = User
            admin_data = dict(
                email=admin_email,
                password=admin_password,
            )

            user_manager.create_superuser(**admin_data)

            self.stdout.write(
                self.style.SUCCESS(f"Administrator {admin_data['email']}  with password '{admin_data['password']}'")
            )
