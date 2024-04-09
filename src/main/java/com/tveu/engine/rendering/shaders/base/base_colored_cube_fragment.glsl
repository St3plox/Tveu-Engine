#version 330 core

out vec4 FragColor;

uniform vec3 objectColor;
//uniform vec3 lightColor;

void main()
{
    FragColor = vec4(vec3(1.0, 0.0, 1.0) * objectColor, 1.0);
}