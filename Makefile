BRANCH=gh-pages
DIR=target/demo/

dev:
	lein figwheel

build:
	lein clean
	lein package

test: build
	python -m http.server --directory public/

site: build
	git branch -D $(BRANCH) || echo 'skip'
	rm -rf $(DIR)
	cp -r ./public $(DIR)
	git --git-dir=.git --work-tree="$(DIR)" checkout --orphan $(BRANCH)
	git --git-dir=.git --work-tree="$(DIR)" add .
	git --git-dir=.git --work-tree="$(DIR)" commit -m 'Build site'
	git --git-dir=.git --work-tree="$(DIR)" checkout main -f
	rm -rf $(DIR)

deploy: site