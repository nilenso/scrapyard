var Scrapyard = Scrapyard || {};

Scrapyard.NewIdea = function(container) {
  var needSelect = container.find(".new-idea-form-need");
  $(needSelect).select2({
      tags: [""],
      tokenSeparators: [",", " "]
  });
};
