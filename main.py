import os
import subprocess
import argparse

def build_apk(project_path):
    gradlew_path = os.path.join(project_path, 'gradlew')
    if not os.path.isfile(gradlew_path):
        print("Error: gradlew not found. Is this a standard Android project?")
        return
    try:
        subprocess.check_call(['chmod', '+x', gradlew_path])
        subprocess.check_call([gradlew_path, 'assembleDebug'], cwd=project_path)
        print("Build finished! Check app/build/outputs/apk/debug/ for your APK.")
    except subprocess.CalledProcessError as e:
        print(f"Build failed: {e}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Turn any Android project into an APK in just a second.")
    parser.add_argument("project_path", help="Path to your Android project")
    args = parser.parse_args()
    build_apk(args.project_path)