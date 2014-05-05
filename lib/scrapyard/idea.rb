module Scrapyard
  class Idea < ActiveRecord::Base
    validates_presence_of :title
  end
end
