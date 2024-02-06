#version 330 core

layout (location = 0) in vec3 aPos;   // Position
layout (location = 1) in vec3 aColor; // Color

out vec3 interpolatedColor; // Output interpolated color to the fragment shader

void main()
{
    gl_Position = vec4(aPos, 1.0);
    interpolatedColor = aColor; // Pass the color to the fragment shader
}
