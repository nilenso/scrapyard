var Scrapyard = Scrapyard || {};

Scrapyard.NewIdea = function(container) {
  var needsSelect = container.find(".new-idea-form-needs");
  $(needsSelect).select2({
      tags: [""],
      tokenSeparators: [",", " "]
  });

  var toolsSelect = container.find(".new-idea-form-tools");
  $(toolsSelect).select2({
      tags: [""],
      tokenSeparators: [",", " "]
  });

  var constraintsSelect = container.find(".new-idea-form-constraints");
  $(constraintsSelect).select2({
      tags: [""],
      tokenSeparators: [",", " "]
  });
};
