from django.urls import path
from . import views


urlpatterns = [
    path('', views.article_list, name='article_list'),
    path('edit/<int:article_id>/', views.article_edit, name='article_edit'),
    path('delete/<int:article_id>/', views.article_delete, name='article_delete'),
]