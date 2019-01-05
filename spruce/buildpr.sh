echo "Checking format..."
ktlint --color
echo "Compiling..."
kotlinc PageRank.kt -include-runtime -d PageRank.jar
echo "Done"
